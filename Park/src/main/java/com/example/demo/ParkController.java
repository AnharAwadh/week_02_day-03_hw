package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/park")
class ParkController {


    private ArrayList<Park> parks = new ArrayList<>();

    @GetMapping
    public ArrayList<Park> getPark() {

        return parks;
    }

    @PostMapping
    public ResponseEntity addPark(@RequestBody @Valid Park park, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            ResponseApi responseApi = new ResponseApi(message, 400, error.getFieldError());
            return ResponseEntity.status(400).body(responseApi);
        }


        parks.add(park);
        return ResponseEntity.status(200).body(parks);
    }

    @PutMapping
    public ResponseEntity editPar(@PathVariable int index, @RequestBody @Valid Park park, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            ResponseApi responseApi = new ResponseApi(message, 400, error.getFieldError());
            return ResponseEntity.status(400).body(responseApi);
        }


        parks.set(index, park);
        return ResponseEntity.status(200).body(parks);
    }
    @DeleteMapping("{index}")
    public ResponseEntity deleteride(@PathVariable @Valid int index){

        parks.remove(index);
        return ResponseEntity.status(200).body(parks);
    }
    @PutMapping("/sell")

    public ResponseEntity<ResponseApi> Deposit(@RequestBody Sell sell){
        Park finedCTickit = null;

        for (Park park : parks) {
            if(park.getRideid().equals(sell.getId())) {
                finedCTickit =park ;
                break;
            }
        }
        if(finedCTickit == null) {
            return  ResponseEntity.status(400).body(new ResponseApi("invalid id",400,null));
        }
        if(finedCTickit.getTicket() <= 0){
            return ResponseEntity.status(400).body(new ResponseApi("the tictkt is available",400,null));
        }
        if(sell.getAmount()>=finedCTickit.getPrice()) {
            return ResponseEntity.status(400).body(new ResponseApi("you do not have price", 400, null));
        }
        finedCTickit.setTicket(finedCTickit.getTicket() - 1);
        return  ResponseEntity.status(200).body(new ResponseApi("ticket purchased",200,null));




    }

      @PutMapping ("/take/{ticketId}/{refils}")
     public ResponseEntity refil(@PathVariable int refils, @PathVariable int ticketId){
          Park finedCTickit = null;

          for (Park park : parks) {
              if(park.getRideid().equals(ticketId)) {
                  finedCTickit =park ;
                  break;
              }
          }

          if(finedCTickit == null) {
              return ResponseEntity.status(400).body(new ResponseApi("can't found ticket",400,null));
          } else {
              if(finedCTickit.getTicket() == 0) {
                  finedCTickit.setTicket(refils);
                  return ResponseEntity.status(200).body(new ResponseApi("ticket refiled",200,null));
              }
              return ResponseEntity.status(400).body(new ResponseApi("you can't refile ticket",400,null));

          }
     }

}

