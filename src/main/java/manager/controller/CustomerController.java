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

import java.util.Optional;


@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProvinceService provinceService;

    @ModelAttribute("province")
    public Page<Province> provinces(Pageable pageable) {
        return provinceService.findAll(pageable);
    }

    @GetMapping("/customer")
    public ModelAndView listCustomer(@RequestParam("search") Optional<String> search, @PageableDefault(size = 5) Pageable pageable) {
        Page<Customer> customers;
        if (search.isPresent()) {
            customers = customerService.findAllByFirstNameContaining(search.get(), pageable);
            ModelAndView modelAndView = new ModelAndView("/customer/list");
            modelAndView.addObject("customers", customers);
            modelAndView.addObject("search", search.get());
            return modelAndView;
        }
        customers = customerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);


        return modelAndView;
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewCustomer(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/view");
        modelAndView.addObject("customers", customer);
        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public ModelAndView editCustomerForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/customer/update")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        redirectAttributes.addFlashAttribute("message", "fucking");
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView createCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer, RedirectAttributes redirectAttributes) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        redirectAttributes.addFlashAttribute("message", "new fucking");
        return modelAndView;

    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCustomerForm(@PathVariable Long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("/customer/delete");
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @PostMapping("/customer/delete")
    private ModelAndView deleteCustomer(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        customerService.remove(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/customer");
        redirectAttributes.addFlashAttribute("message", "delete fucking");
        return modelAndView;
    }
}