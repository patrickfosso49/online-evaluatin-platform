package com.projet.classwork.service;

import com.projet.classwork.model.Proposition;
import com.projet.classwork.model.Question;
import com.projet.classwork.model.Questionnaire;
import com.projet.classwork.model.Submission;
import com.projet.classwork.repository.evaluation.QuestionRepository;
import com.projet.classwork.repository.evaluation.QuestionnaireRepository;
import com.projet.classwork.repository.evaluation.SubmisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubmissionServiceImpl implements SubmissionService{

    @Autowired
    SubmisionRepository submisionRepository;

    @Autowired
    PropositionService propositionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Override
    public Submission save(Submission submission) {
        return submisionRepository.save(submission);
    }

    @Override
    public Optional<Submission> loadById(Long ID) {
        return submisionRepository.findById(ID);
    }

    @Override
    public List<Submission> fetchAllSubmission() {
        return  submisionRepository.findAll();
    }

    @Override
    public Optional<Submission> findSubmissionByID(Long id) {
        return  submisionRepository.findById(id);
    }

    @Override
    public void deleteSubmissionById(Long ID) {
        submisionRepository.deleteById(ID);
    }

    @Override
    public Submission updateSubmission(Submission submission, Long ID) {
        Submission submissionToUpdate = submisionRepository.findById(ID).get();

        if(Objects.nonNull(submission.getTime()) &&
                !"".equalsIgnoreCase(submission.getTime().toString())){
            submissionToUpdate.setTime(submission.getTime());
        }  if(!"".equalsIgnoreCase(submission.getMark()+"")){
            submissionToUpdate.setMark(submission.getMark());
        }
        return submisionRepository.save(submissionToUpdate);
    }

    @Override
    public Submission correctSubmission(Long submissionID) {
        boolean hasMatched = false;
        Long propID = 0L;
        //get proposition from submission
       List<Proposition> Allpropositions =  propositionService.fetchAllProposition();// all the propositions from db
        for(int i = 0; i < Allpropositions.size(); i++){
            if(Allpropositions.get(i).getSubmission().getId() == submissionID){// the proposition matches with the submission
                hasMatched = true;
                 propID = Allpropositions.get(i).getId();
                break;
            }
        }
        Submission submission = new Submission();
        if (hasMatched){
           submission = submisionRepository.findById(submissionID).get();
            int mark = 0;

            mark += getMarkFromQuestion(questionRepository.findById(propositionService.getQuestionID(propID)).get(), propositionService.findPropositionById(propID).get());
            submission.setMark(mark);
        }
        return submisionRepository.save(submission);
    }

    public int getMarkFromQuestion(Question question, Proposition proposition) {
        int mark = 0;
        for(int i = 0; i < question.getAnswer().getValues().size(); i++){
            for(int j = 0; j < question.getAnswer().getValues().size(); j++){
                if(proposition.getValues().get(i).equalsIgnoreCase(question.getAnswer().getValues().get(j))){
                    mark = mark + 1;
                }
            }
        }
        return mark;
    }
}
