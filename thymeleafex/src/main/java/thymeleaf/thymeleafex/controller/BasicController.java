package thymeleaf.thymeleafex.controller;

import lombok.Data;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello <b>Spring</b>");

        return "basic/text-basic";
    }


    @GetMapping("/variable")
    public String variable(Model model) {
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>(Arrays.asList(userA, userB));

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }
    @GetMapping("/basic-objects")
    public String basicObject(HttpSession httpSession) {
        httpSession.setAttribute("sessionData", "Hello Session");
        return "basic/basic-object";
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }
    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring");

        return "basic/operation";
    }


    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }
    private void addUsers(Model model){
        List<User> users =Arrays.asList(new User("userA", 10),
                new User("userB", 20),
                new User("userC", 30));

        model.addAttribute("users", users);
    }

    @GetMapping("/condition")
    public String condition(Model model){
        addUsers1(model);
        return "basic/condition";
    }
    private void addUsers1(Model model){
        List<User> users =Arrays.asList(new User("userA", 10),
                new User("userB", 20),
                new User("userC", 30));

        model.addAttribute("users", users);
    }


    @Data
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}


