package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.data.JobDataImporter;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.plaf.synth.SynthTextAreaUI;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        Job someJob = jobData.findById(id);
        model.addAttribute(someJob);
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then


        String name = jobForm.getName();
        int id = jobForm.getEmployerId();
        Employer employer = jobData.getEmployers().findById(id);
        Location location = jobForm.getLocations().get(0);
        PositionType positionType = jobForm.getPositionTypes().get(0);
        CoreCompetency coreCompetency = jobForm.getCoreCompetencies().get(0);

        Job newJob = new Job();

        System.out.println(jobForm.getEmployerId());
        System.out.println();

        newJob.setName(name);
        newJob.setEmployer(employer);
        newJob.setLocation(location);
        newJob.setPositionType(positionType);
        newJob.setCoreCompetency(coreCompetency);


        if(jobForm.getName() == ""){

        errors.addAllErrors(errors);
            return "redirect:/job/add";
        }

        jobData.add(newJob);

        return "redirect:?id="+newJob.getId();

    }
}
