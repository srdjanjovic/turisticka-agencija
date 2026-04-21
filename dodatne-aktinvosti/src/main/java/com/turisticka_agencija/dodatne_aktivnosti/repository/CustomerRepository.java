package com.turisticka_agencija.dodatne_aktivnosti.repository;

import com.turisticka_agencija.dodatne_aktivnosti.model.Customer;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends Neo4jRepository<Customer, Long> {
}