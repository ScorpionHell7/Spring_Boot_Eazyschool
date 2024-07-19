package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;
@Slf4j
@Controller
public class ContactController {

//    private static Logger log = Logger.getLogger(ContactController.class.getName());
    private ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value={"/contact"})
    public String displayContactPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }
//    @PostMapping(value = "saveMsg")
//    public ModelAndView SaveMessage(@RequestParam String name, @RequestParam String mobileNum,@RequestParam String email,@RequestParam String subject,@RequestParam String message) {
//        log.info("Name :"+name);
//        log.info("mobileNum :"+mobileNum);
//        log.info("email :"+email);
//        log.info("subject :"+subject);
//        log.info("message :"+message);
//        return  new ModelAndView("redirect:/contact");
//    }
    @RequestMapping(value="saveMsg", method = RequestMethod.POST)
    public String SaveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if (errors.hasErrors()) {
            log.error("Conatct form failed due to : "+errors.toString());
            return "contact.html";
        }
        contactService.saveMessageDetails(contact);
//        contactService.setCounter(contactService.getCounter()+1);
//        log.info("Number of times contact submitted : "+contactService.getCounter());
        return "redirect:/contact";
    }
    @RequestMapping("/displayMessages")
    public ModelAndView displayMessages(Model model) {
        List<Contact> contactMsgs = contactService.findMsgWithOpenStatus();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        modelAndView.addObject("contactMsgs", contactMsgs);
        return modelAndView;
    }
    @RequestMapping(value = "/closeMsg",method = RequestMethod.GET)
    public String CloseMsg(@RequestParam int id){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }
}
