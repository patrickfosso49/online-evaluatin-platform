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
    public Submission correctSubmission(Submission submission) {
        //get proposition from submission
        List<Proposition> propositionListForOneSubmission =  propositionService.fetchAllProposition();// all the propositions from db
        for(int i = 0; i < propositionListForOneSubmission.size(); i++){
            if(propositionListForOneSubmission.get(i).getSubmission().getId() == submission.getId()){// the proposition matches with the submission
                propositionListForOneSubmission.set(i, propositionListForOneSubmission.get(i));// proposition list for a single submission
            }
        }

        List<Question> questionListOneSubmission = questionRepository.findAll();
        List<Questionnaire> questionnaireListOneSubmission = questionnaireRepository.findAll();
        int mark = 0;
        for(int i = 0; i<questionListOneSubmission.size(); i++){
           mark += getMarkFromQuestion(propositionListForOneSubmission.get(i).getQuestion(),  propositionService.findPropositionById(propositionListForOneSubmission.get(i).getId()).get());
            propositionListForOneSubmission.get(i).getQuestion();
        }
        submission.setMark(mark);
        submisionRepository.save(submission);
        return submission;
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
