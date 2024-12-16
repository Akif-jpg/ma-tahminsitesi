package com.metbetestimate.betestimate.restcontroller;

import java.util.List;
import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.metbetestimate.betestimate.constants.DbStaticStr;
import com.metbetestimate.betestimate.models.BetModel;
import com.metbetestimate.betestimate.models.DateRange;
import com.metbetestimate.betestimate.repositories.BetJdbcRepository;
import com.metbetestimate.betestimate.repositories.Repository;


@RestController
@RequestMapping(value = "api")
public class BetPostsController{


    Logger logger = LoggerFactory.getLogger(BetPostsController.class);
    private Repository<BetModel> betRepository;

    BetPostsController(BetJdbcRepository betRepository){
        this.betRepository = betRepository;
    }

    @GetMapping("/listofallbets")
    @ResponseBody
    public List<BetModel> getListOfAllBets(){
        
        return betRepository.List();
    }

    @GetMapping("/getBet/{betId}")
    @ResponseBody
    public BetModel getBetById(@PathVariable int betId){
        return (BetModel) betRepository.get(betId);
    }

    @PostMapping("sec/createBet")    
    public void createBet(@RequestBody BetModel betModel){        
        logger.info("post received by controller post title = "  + betModel.getTitle());
        betRepository.create(betModel);
    }

    @PostMapping("sec/updateBet")
    public void updateBet(@RequestBody BetModel betModel){
        logger.info("update request received by controller id = +%d".formatted(betModel.getId()));
        betRepository.update(betModel, betModel.getId());
        
    }

    @PostMapping("sec/deleteBet/{betId}")
    public void deleteBet(@PathVariable int betId){
        betRepository.delete(betId);        
    }

    @PostMapping("/listBetByDate")
    public List<BetModel> listBetByDate(@RequestBody DateRange betDateRange){
        String sql = "SELECT * FROM "+DbStaticStr.BET_TABLE_NAME+" WHERE posted_at  BETWEEN '%s' AND '%s'".formatted(betDateRange.getDate1(),betDateRange.getDate2());
        return betRepository.listFromQuery(sql);
        
    }


}