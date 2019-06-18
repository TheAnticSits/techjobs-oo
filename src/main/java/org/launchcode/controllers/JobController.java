package org.launchcode.controllers;

import org.launchcode.models.Job;
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


        Job newJob = new Job();

        newJob.setName(jobForm.getName());

        newJob.setEmployer(jobForm.getEmployer());
        newJob.setLocation(jobForm.getLocation());

        newJob.setPositionType(jobForm.getPositionType());
        newJob.setCoreCompetency(jobForm.getCoreCompetency());

        System.out.println(jobForm.getLocations());

        jobData.add(newJob);

        System.out.println(newJob.getName());
        System.out.println((newJob.getLocation()));

        return "redirect:?id="+newJob.getId();

    }
}
