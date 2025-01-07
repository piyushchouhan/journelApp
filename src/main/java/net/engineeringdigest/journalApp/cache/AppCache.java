package net.engineeringdigest.journalApp.cache;

import net.engineeringdigest.journalApp.entity.JournalAppConfig;
import net.engineeringdigest.journalApp.repository.JournalAppConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public enum keys{
        WEATHER_API,;
    }
    public Map<String, String> APP_CACHE = new HashMap<>();

    @Autowired
    private JournalAppConfigRepository journalAppConfigRepository;

    @PostConstruct
    public void init(){
        List<JournalAppConfig> all = journalAppConfigRepository.findAll();
        for(JournalAppConfig journalAppConfig : all){
            APP_CACHE.put(journalAppConfig.getKey(), journalAppConfig.getValue());
        }
    }
}

