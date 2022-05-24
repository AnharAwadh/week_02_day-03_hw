package com.example.employe;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployController {

    private ArrayList<Employe> employes = new ArrayList<>();

    @GetMapping
    public ArrayList<Employe> getEmployee() {

        return employes;
    }

    @PostMapping
    public ResponseEntity addEmployee(@RequestBody @Valid Employe employe, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            ResponseApi responseApi = new ResponseApi(message, 400, error.getFieldError());
            return ResponseEntity.status(400).body(responseApi);
        }


        employes.add(employe);
        return ResponseEntity.status(200).body(employes);
    }

    @PutMapping
    public ResponseEntity editEmployee(@PathVariable int index, @RequestBody @Valid Employe employe, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            ResponseApi responseApi = new ResponseApi(message, 400, error.getFieldError());
            return ResponseEntity.status(400).body(responseApi);
        }


        employes.set(index, employe);
        return ResponseEntity.status(200).body(employes);
    }
    @DeleteMapping("{index}")
    public ResponseEntity deleteride(@PathVariable @Valid int index){

        employes.remove(index);
        return ResponseEntity.status(200).body("The Employee deleted");
    }

    @GetMapping("apply-leave/{emmployeId}")
    public ResponseEntity applyLeave(@PathVariable String emmployeId) {
        Employe foundedEmployee = null;
        for (Employe employe : employes) {
            if(employe.getId().equals(emmployeId)) {
                foundedEmployee = employe;
                break;
            }
        }
        if(foundedEmployee == null) {
            return ResponseEntity.status(200).body("employee is not founded");
        }
        if(foundedEmployee.isOnleave()) {
            return ResponseEntity.status(400).body("employee is on leave");
        }
        if(foundedEmployee.getAnnualLeave() == 0) {
            return ResponseEntity.status(400).body("employee is 0 days annual leave");
        }
        foundedEmployee.setAnnualLeave(foundedEmployee.getAnnualLeave() - 1);
        foundedEmployee.setOnleave(true);
        return ResponseEntity.ok("");

    }




}
