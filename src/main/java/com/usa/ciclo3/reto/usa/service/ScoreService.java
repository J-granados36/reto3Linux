package com.usa.ciclo3.reto.usa.service;

import com.usa.ciclo3.reto.usa.model.Message;
import com.usa.ciclo3.reto.usa.model.Score;
import com.usa.ciclo3.reto.usa.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getAll(){
        return scoreRepository.getAll();
    }

    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }

    public Score Save(Score score){
        if (score.getIdScore()==null){
            return scoreRepository.save(score);
        }else{
            Optional<Score> Scaux=scoreRepository.getScore(score.getIdScore());
            if (Scaux.isEmpty()){
                return scoreRepository.save(score);
            }else{
                return score;
            }
        }
    }

    public Score update(Score score){
        if (score.getIdScore() != null){
            Optional<Score> Sc= scoreRepository.getScore(score.getIdScore());
            if (Sc.isEmpty()){
                if (score.getMessageText() != null){
                    Sc.get().setMessageText(score.getMessageText());
                }
                if (score.getStars() != null){
                    Sc.get().setStars(score.getStars());
                }
                scoreRepository.save(Sc.get());
                return Sc.get();
            }else{
                return score;
            }
        }else {
            return score;
        }
    }


    public boolean deleteScore(int id){
        Boolean dSc = getScore(id).map(score -> {
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
        return dSc;
    }

}
