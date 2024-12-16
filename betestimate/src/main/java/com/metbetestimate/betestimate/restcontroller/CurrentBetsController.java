package com.metbetestimate.betestimate.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metbetestimate.betestimate.models.BetModel;
import com.metbetestimate.betestimate.repositories.CurrentBetJdbcRepository;
import com.metbetestimate.betestimate.repositories.Repository;

@RestController
@RequestMapping(value = "/api/")
public class CurrentBetsController {

    Logger logger = LoggerFactory.getLogger(BetPostsController.class);
    private Repository<BetModel> currentBetRepository;

    public CurrentBetsController(CurrentBetJdbcRepository currentBetRepository) {
        this.currentBetRepository = currentBetRepository;
    }

    @GetMapping("listofallcbets")
    @ResponseBody
    public List<BetModel> getAllCurrentBetModels() {
        return currentBetRepository.List();
    }

    @GetMapping("/getCBet/{betId}")
    @ResponseBody
    public BetModel getBetById(@PathVariable int betId){
        return (BetModel) currentBetRepository.get(betId);
    }

    @PostMapping("sec/createCBet")    
    public void createBet(@RequestBody BetModel betModel){        
        logger.info("post received by controller post title = "  + betModel.getTitle());
        currentBetRepository.create(betModel);
    }

    @PostMapping("sec/updateCBet")
    public void updateBet(@RequestBody BetModel betModel){
        logger.info("update request received by controller id = +%d".formatted(betModel.getId()));
        currentBetRepository.update(betModel, betModel.getId());
        
    }

    @PostMapping("sec/deleteCBet/{betId}")
    public void deleteBet(@PathVariable int betId){
        currentBetRepository.delete(betId);        
    }

    
}
