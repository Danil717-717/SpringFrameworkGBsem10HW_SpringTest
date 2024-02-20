package ru.springgb.sem10HW.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.springgb.sem10HW.model.Session;
import ru.springgb.sem10HW.repository.SessionRepository;

import java.util.List;


@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    public Session save(Session session) {
        return sessionRepository.save(session);
    }


    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }

    public Session updateSession(Long id, Session session) {
        Session existingSession = findById(id);
        existingSession.setName(session.getName());
        return sessionRepository.save(existingSession);
    }


    public void delete(Long id) {
        sessionRepository.deleteById(id);
    }
}
