package com.ans.petp.service;

import com.ans.petp.entity.JournalEntry;
import com.ans.petp.entity.User;
import com.ans.petp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(savedEntry);
            userService.saveUser(user);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("an error occurred while saving the enter" + e);
        }

    }

    public void saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryRepository.save(journalEntry);
    }

    public Optional<JournalEntry> getJournalById(ObjectId id) {
        return journalEntryRepository.findById(String.valueOf(id));
    }

    @Transactional
    public void deleteEntry(ObjectId id, String username) {
        try {
            User user = userService.findByUsername(username);
            boolean removed = user.getJournalEntries().removeIf(journalEntry -> journalEntry.getId().equals(id));
            if(removed)
            {
                userService.saveUser(user);
                journalEntryRepository.deleteById(String.valueOf(id));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }


    }

}
