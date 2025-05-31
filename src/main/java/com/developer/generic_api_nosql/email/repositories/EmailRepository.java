package com.developer.generic_api_nosql.email.repositories;

import com.developer.generic_api_nosql.email.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, String>{
}
