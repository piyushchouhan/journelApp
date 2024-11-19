package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping("/abc")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping("/abc")
    public JournalEntry addEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @GetMapping("/abc/{id}")
    public JournalEntry getJournalById(@PathVariable ObjectId id){
        return journalEntryService.getJournalById(id).orElse(null);
    }

    @DeleteMapping("/abc/{id}")
    public boolean deleteJournalById(@PathVariable ObjectId id){
       journalEntryService.deleteJournalById(id);
       return true;
    }

    @PutMapping("/abc/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry entry){
        JournalEntry old = journalEntryService.getJournalById(id).orElse(null);
        if (old != null){
            old.setTitle(entry.getTitle() != null && !entry.getTitle().isEmpty() ? entry.getTitle() : old.getTitle());
            old.setContent(entry.getContent() != null && !entry.getContent().isEmpty() ? entry.getContent() : old.getContent());
        }
        journalEntryService.saveEntry(old);
        return old;
    }
}
