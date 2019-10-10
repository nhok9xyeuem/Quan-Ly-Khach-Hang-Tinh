package manager.controller;

import manager.model.Customer;
import manager.model.Province;
import manager.service.CustomerService;
import manager.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProvinceController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private CustomerService customerService;

    @ModelAttribute("customer")
    public Page<Customer> customers(Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @GetMapping("/province")
    public ModelAndView listProvince(@PageableDefault(size = 2) Pageable pageable) {
        Page<Province> provinces = provinceService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("province/list");
        modelAndView.addObject("province", provinces);
        return modelAndView;
    }

    @GetMapping("/create-province")
    public ModelAndView createProvinceForm() {
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create-province")
    public ModelAndView createProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        redirectAttributes.addFlashAttribute("message", "checking Ok");
        return modelAndView;
    }

    @GetMapping("/edit-province/{id}")
    public ModelAndView editProvinceForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("province/edit");
        modelAndView.addObject("province", province);
        return modelAndView;

    }

    @PostMapping("/edit-province")
    public ModelAndView editProvince(@ModelAttribute("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        redirectAttributes.addFlashAttribute("message", "update Ok");
        return modelAndView;
    }

    @GetMapping("/delete-province/{id}")
    public ModelAndView deleteProvinceForm(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        ModelAndView modelAndView = new ModelAndView("province/delete");
        modelAndView.addObject("province", province);
        return modelAndView;

    }

    @PostMapping("/delete-province")
    public ModelAndView deleteProvince(@ModelAttribute ("province") Province province, RedirectAttributes redirectAttributes) {
        provinceService.remove(province.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/province");
        redirectAttributes.addFlashAttribute("message", "Delete Finish");
        return modelAndView;
    }

    @GetMapping("/view-province/{id}")
    public ModelAndView viewCustomer(@PathVariable Long id) {
        Province province = provinceService.findById(id);
        Iterable<Customer> customers = customerService.findAllByProvince(province);
        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customer",customers);
        return modelAndView;
    }
}