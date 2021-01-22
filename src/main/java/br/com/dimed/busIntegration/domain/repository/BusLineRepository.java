package br.com.dimed.busIntegration.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dimed.busIntegration.domain.model.BusLine;

@Repository
public interface BusLineRepository extends JpaRepository<BusLine, Long> {

}
