package com.example.toDo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class TaskController {

    private TaskDao taskDao = new StaticDao();


 //   @ResponseBody   //metoda zeby zwrocic stringa
    @PostMapping("/tasks")
        public String create(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam(required = false) boolean finished,
                             ModelMap modelMap
        ) {


        Task task = new Task(name,description,finished);
        modelMap.put("task",task);
        taskDao.addTask(task);
        return "redirect:/tasks";
        //przejdz do sciezki /task z metoda http
        //    return "added"+task;

        }

        @GetMapping("/tasks")
    public String index(ModelMap modelMap) {
        modelMap.put("tasks",taskDao.findAll());
        return "index";
        }

    @GetMapping("/finished")
    public String finished(ModelMap modelMap){
        modelMap.put("tasks",taskDao.findByStatus(true));
        return "index";
    }


        @GetMapping("/unfinished")
    public String unfinished(ModelMap modelMap){
        modelMap.put("tasks",taskDao.findByStatus(false));
        return "index";
        }

        @GetMapping("/")
    public String add(){
        return "add";
        }

}
