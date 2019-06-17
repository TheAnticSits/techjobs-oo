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
    public String add(Model model, @Valid JobForm jobForm, Errors errors, int id) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        /*Validate the form in the add handler of JobController, and if it's valid, create a new Job object and add it to the data layer by calling jobData.add(newJob).

                To create the new job, you'll need to find the pre-existing objects for all fields other than name (employer, location, etc). Do this using the methods discussed above. Refer to the constructor in Job to make sure you list the objects in the correct order when calling it.

        Once you've created the new job, redirect to the single job display page that you created above. If the jobForm model object fails validation, display the form again.
        */

        Job newJob = new Job();
        id = jobForm.getEmployerId();
        model.addAttribute(jobData.findById(id));
        System.out.println(jobForm.getName());
        System.out.println(jobForm.getEmployerId());



        jobData.add(newJob);

        return "redirect:/job";

/*        Job newJob = new Job();
        /*model.addAttribute(someJob.getEmployer());
        model.addAttribute((someJob.getLocation()));
        model.addAttribute(someJob.getPositionType());
        model.addAttribute(someJob.getCoreCompetency());*/
//        model.addAttribute(jobForm.getName());
    }
}
