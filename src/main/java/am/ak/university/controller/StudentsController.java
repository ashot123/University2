package am.ak.university.controller;

import am.ak.university.entities.DepartmentType;
import am.ak.university.entities.Student;
import am.ak.university.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/")
public class StudentsController {


   /* @ModelAttribute("frequencies")
    public DepartmentType[] depTypes2() {
        return DepartmentType.values();
    }
*/
    @ModelAttribute("frequencies")
    public String[] depTypes() {
        String[] myNames = new String[DepartmentType.values().length];
        for (int i= 0;  i <  DepartmentType.values().length; i++){
            myNames[i] = DepartmentType.values()[i].getDepartmentType();
        }
        return myNames;
    }

    @Autowired
    UserService userService;

    /**
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {

        List<Student> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "userList";
    }


    /**
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        Student student = new Student();
        model.addAttribute("departmentType", depTypes());
        model.addAttribute("student", student);
        model.addAttribute("edit", false);

        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
    public String saveUser(@Valid Student student, BindingResult result,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        /*
         * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
         * and applying it on field [sso] of Model class [User].
         *
         * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
         * framework as well while still using internationalized messages.
         *
         */
        /*if(!userService.isUserSSOUnique(student.getId(), student.getSsoId())){
            FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/ // TODO

        userService.saveStudent(student);

        model.addAttribute("success", "User " + student.getFirstName() + " " + student.getLastName() + " registered successfully");
        //return "success";
        return "registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-user-{id}"}, method = RequestMethod.GET)
    public String editUser(@PathVariable String id, ModelMap model) {
        Student student = userService.findById(id);
        model.addAttribute("departmentType", depTypes());
        //DepartmentType[] array = student.getDepartment().getDepartmentType();
        //model.addAttribute("departmentType2", ((DepartmentType)student.getDepartment().getDepartmentType());
        model.addAttribute("student", student);
        model.addAttribute("edit", true);
        return "registration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-user-{id}"}, method = RequestMethod.POST)
    public String updateUser(@Valid Student student, BindingResult result,
                             ModelMap model, @PathVariable String id) {

        if (result.hasErrors()) {
            return "registration";
        }

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/


        userService.updateUser(student);

        model.addAttribute("success", "User " + student.getFirstName() + " " + student.getLastName() + " updated successfully");
        return "registrationsuccess";
    }

    /**
     * This method will delete an user by it's SSOID value.
     */
    /*@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
    public String deleteUser(@PathVariable String ssoId) {
        userService.deleteUserBySSO(ssoId);
        return "redirect:/list";
    }*/

    /**
     * This method will delete an user by it's SSOID value.
     */
    @RequestMapping(value = {"/delete-user-{id}"}, method = RequestMethod.GET)
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "redirect:/list";
    }
}
