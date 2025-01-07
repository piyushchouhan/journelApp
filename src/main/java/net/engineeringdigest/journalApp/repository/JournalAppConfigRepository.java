package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.JournalAppConfig;
import net.engineeringdigest.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalAppConfigRepository extends MongoRepository<JournalAppConfig, ObjectId>{

}
