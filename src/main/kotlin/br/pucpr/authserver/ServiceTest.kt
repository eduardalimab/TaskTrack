package br.pucpr.authserver

import br.pucpr.authserver.projects.Project
import br.pucpr.authserver.projects.ProjectRepository
import br.pucpr.authserver.projects.ProjectService
import br.pucpr.authserver.projects.requests.ProjectRequest
import br.pucpr.authserver.task.Task
import br.pucpr.authserver.task.TaskRepository
import br.pucpr.authserver.task.TaskService
import br.pucpr.authserver.task.CreateTaskRequest

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ServiceTest {

    private val projectRepository = mockk<ProjectRepository>()
    private val taskRepository = mockk<TaskRepository>()
    private val projectService = ProjectService(projectRepository, taskRepository)
    private val taskService = TaskService(taskRepository)

    @Test
    fun `should create a project`() {
        val projectRequest = ProjectRequest(
            name = "New Project",
            description = "New Project Description",
            start_date = "2021-01-01",
            end_date = "2021-12-31",
            status = "IN_PROGRESS"
        )
        val project = Project(
            id = 1,
            name = projectRequest.name,
            description = projectRequest.description,
            start_date = projectRequest.start_date,
            end_date = projectRequest.end_date,
            status = projectRequest.status
        )

        every { projectRepository.save(any<Project>()) } returns project

        val result = projectService.save(projectRequest)

        assertEquals(project.id, result.id)
        assertEquals(project.name, result.name)
        assertEquals(project.description, result.description)

        verify { projectRepository.save(any<Project>()) }
    }

    @Test
    fun `should create a task`() {
        val project = Project(
            id = 1,
            name = "Project 1",
            description = "Description 1",
            start_date = "2021-01-01",
            end_date = "2021-12-31",
            status = "IN_PROGRESS"
        )
        val taskRequest = CreateTaskRequest(
            name = "New Task",
            projectId = project.id
        )
        val task = Task(
            id = 1,
            name = taskRequest.name!!,
            project = project
        )

        every { projectRepository.findById(taskRequest.projectId!!) } returns java.util.Optional.of(project)
        every { taskRepository.save(any<Task>()) } returns task

        val result = taskService.insert(taskRequest.toTask(project))

        assertEquals(task.id, result.id)
        assertEquals(task.name, result.name)
        assertEquals(task.project.id, result.project.id)

        verify { taskRepository.save(any<Task>()) }
    }
}