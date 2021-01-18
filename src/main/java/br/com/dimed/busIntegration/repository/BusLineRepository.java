package br.com.dimed.busIntegration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.dimed.busIntegration.model.BusLine;

@Repository
public interface BusLineRepository extends MongoRepository<BusLine, String>{

}
