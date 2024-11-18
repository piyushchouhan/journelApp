package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2/journal")
public class JournalEntryControllerV2 {



    @GetMapping("/abc")
    public List<JournalEntry> getAll() {
        return null;
    }

    @PostMapping("/abc")
    public JournalEntry addEntry(@RequestBody JournalEntry entry){
       return null;
    }

    @GetMapping("/abc/{id}")
    public JournalEntry getJournalById(@PathVariable Long id){
        return null;
    }

    @DeleteMapping("/abc/{id}")
    public JournalEntry deleteJournalById(@PathVariable Long id){
       return null;
    }

    @PutMapping("/abc/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry entry){
        return null;
    }
}
