package com.adb.web.app.controllers;

import com.adb.web.app.domain.ContactEmail;
import com.adb.web.app.services.EmailService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping(path="/email")
public class ContactController {

    private Logger logger = Logger.getLogger(ContactController.class.getName());

    @Autowired
    private EmailService emailService;

    @PostMapping(path="/sendContactEmail")
    public @ResponseBody
    String sendContactEmail (@RequestBody ContactEmail contactEmail) {
        logger.info("Sending Contact Email with Data: "+contactEmail);
        try {
            emailService.sendContactEmail(contactEmail);
        } catch (Exception e) {
            logger.info("Exception when sending contact email: "+ e.getMessage());
        }

        Gson gson = new Gson();

        return gson.toJson("Contact Email Sent Successfully", String.class);
    }


}
