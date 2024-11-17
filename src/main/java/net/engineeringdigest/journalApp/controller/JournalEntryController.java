package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    private Map<Long, JournalEntry> journelEntries = new HashMap<>();

    @GetMapping("/abc")
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journelEntries.values());
    }

    @PostMapping("/abc")
    public JournalEntry addEntry(@RequestBody JournalEntry entry){
        entry.setId(journelEntries.size() + 1);
        journelEntries.put(entry.getId(), entry);
        return entry;
    }

    @GetMapping("/abc/{id}")
    public JournalEntry getJournalById(@PathVariable Long id){
        return  journelEntries.get(id);
    }

    @DeleteMapping("/abc/{id}")
    public JournalEntry deleteJournalById(@PathVariable Long id){
        return journelEntries.remove(id);
    }

    @PutMapping("/abc/{id}")
    public JournalEntry updateJournalById(@PathVariable Long id, @RequestBody JournalEntry entry){
        entry.setId(id);
        return journelEntries.put(id, entry);
    }
}
