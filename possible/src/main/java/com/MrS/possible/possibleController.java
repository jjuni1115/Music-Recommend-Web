package com.MrS.possible;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class possibleController {
    // Home page
    @GetMapping(value="/")
    public String index(){
        return "index";
    }

}








/*

    @ResponseBody
    @PostMapping(value = "/login")
    public String login(member m, HttpSession session){
        return "login";
        return m_dao.try_login(m, session);
        }

    @RequestMapping(value="TestPage.do", method=RequestMethod.POST)
    public String TestPage(Model model, HttpServletRequest request){
        String testInput = (String)request.getParameter(m);

        if (testInput.length() > 0){
            model.addAttribute("Output", "Success");
        }
        else{
           model.addAttribute("Output", "Failed");
        }

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public int register(member m) {
        return m_dao.Register(m);
    }
/*
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
*/
    //@RequestMapping(value = "/logout", method = RequestMethod.GET)
   // public String logout(HttpSession session) {
        //session.removeAttribute("m");
       // return "redirect:/"; }
/*
    @ControllerAdvice
    public class JsonpAdviceController extends AbstractJsonpResponseBodyAdvice {
        public JsonpAdviceController() {
            super("callback");
        }
    }

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
       ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }
*/
//}

