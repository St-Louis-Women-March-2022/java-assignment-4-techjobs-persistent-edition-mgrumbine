package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

    @Autowired
    private JobRepository jobRepository; //added this without being prompted

    @Autowired
    private SkillRepository skillRepository; //added this without being prompted

    @Autowired
    private EmployerRepository employerRepository;

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");

        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute(new Job());
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam(required = false) int employerId, @RequestParam(required = false) List<Skill> skills) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Job");
            model.addAttribute("employers", employerRepository.findAll());
            model.addAttribute("skills", skillRepository.findAll());
            return "add";
        } else {
            Optional<Employer> resultEmployer = employerRepository.findById(employerId);
            if (resultEmployer.isEmpty()) {
                model.addAttribute("title", "Invalid Employer ID: " + employerId);
            } else {
                Employer employer = resultEmployer.get();
                newJob.setEmployer(employer);
            }
            if (skills.isEmpty()) {
                model.addAttribute("title", "Invalid Skill List: " + skills);
            }
            newJob.setSkills(skills);
            jobRepository.save(newJob); //added this without being prompted
            return "redirect:";
        }
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable int jobId) {

        if (jobRepository.existsById(jobId)) {
            model.addAttribute("job", jobRepository.findById(jobId)); //added this whole thing without being prompted
            return "view";
        } else {
            return "redirect:../";
        }
    }

}
