const taskInput = document.getElementById("taskInput");
const addTaskBtn = document.getElementById("addTaskBtn");
const taskList = document.getElementById("taskList");

const fetchTasks = async () => {
    try {
        const response = await fetch("/api/tasks");
        if (!response.ok) throw new Error("Failed to fetch tasks");
        const tasks = await response.json();
        taskList.innerHTML = "";
        tasks.forEach(renderTask);
    } catch (error) {
        console.error(error);
    }
};

addTaskBtn.addEventListener("click", async () => {
    const title = taskInput.value.trim();
    if (!title) return alert("Task cannot be empty!");

    try {
        const response = await fetch(`/api/tasks?title=${encodeURIComponent(title)}`, {
            method: "POST",
        });
        if (!response.ok) throw new Error("Failed to add task");
        taskInput.value = "";
        fetchTasks();
    } catch (error) {
        console.error(error);
    }
});

const renderTask = (task) => {
    const li = document.createElement("li");
    li.className = task.completed ? "done" : "";

    const title = document.createElement("span");
    title.textContent = task.title;

    const actions = document.createElement("div");

    const toggleBtn = document.createElement("button");
    toggleBtn.textContent = task.completed ? "Undo" : "Complete";
    toggleBtn.addEventListener("click", async () => {
        await fetch(`/api/tasks/${task.id}?completed=${!task.completed}`, {
            method: "PUT",
        });
        fetchTasks();
    });

    const deleteBtn = document.createElement("button");
    deleteBtn.textContent = "Delete";
    deleteBtn.addEventListener("click", async () => {
        await fetch(`/api/tasks/${task.id}`, {
            method: "DELETE",
        });
        fetchTasks();
    });

    actions.appendChild(toggleBtn);
    actions.appendChild(deleteBtn);
    li.appendChild(title);
    li.appendChild(actions);
    taskList.appendChild(li);
};

fetchTasks();
