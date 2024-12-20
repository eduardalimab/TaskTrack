package br.pucpr.authserver

import br.pucpr.authserver.roles.Role
import br.pucpr.authserver.roles.RoleRepository
import br.pucpr.authserver.users.User
import br.pucpr.authserver.users.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Component
class Bootstrapper(
    val roleRepository: RoleRepository,
    val userRepository: UserRepository
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole = roleRepository.findByName("ADMIN")
            ?: roleRepository.save(Role(
                name="ADMIN",
                description = "System Administrator")
            ).also {
                roleRepository.save(Role(
                    name="USER",
                    description = "Premium User")
                )
                log.info("ADMIN and USER roles created!")
            }
        if (userRepository.findByRole("ADMIN").isEmpty()) {
            val passwordEncoder = BCryptPasswordEncoder()
            val admin = User(
                email="admin@authserver.com",
                password=passwordEncoder.encode("admin"),
                name="Auth Server Administrator"
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
            log.info("ADMIN user created!")
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(Bootstrapper::class.java)
    }
}
