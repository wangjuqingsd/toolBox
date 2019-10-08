package cn.ytg.toolBox.controller;

import cn.ytg.toolBox.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        return "done";
    }
}
