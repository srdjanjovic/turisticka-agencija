package com.turisticka_agencija.dodatne_aktivnosti.repository;

import com.turisticka_agencija.dodatne_aktivnosti.model.AdditionalActivity;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdditionalActivityRepository extends Neo4jRepository<AdditionalActivity, Long> {

    @Query("MATCH (customer:Customer)-[:REGISTERED_FOR]->(:AdditionalActivity)<-[:REGISTERED_FOR]-(similarCustomer:Customer)-[r:REGISTERED_FOR]->(recommendedActivity:AdditionalActivity)-[partOf:PART_OF]->(arr:Arrangement) " +
            "WHERE id(customer) = $customerId " +
            "AND NOT (customer)-[:REGISTERED_FOR]->(recommendedActivity) " +
            "AND (customer)-[:BOOKED]->(arr) " +
            "WITH recommendedActivity, partOf, arr, COUNT(DISTINCT similarCustomer) AS similarityScore, AVG(r.numberOfPeople) AS avgPeople " +
            "WHERE similarityScore >= 1 AND avgPeople >= 2 " +
            "RETURN recommendedActivity, partOf, arr " +
            "ORDER BY similarityScore DESC, avgPeople DESC")
    List<AdditionalActivity> recommendActivitiesBySimilarCustomers(Long customerId);

    @Query("MATCH (customer:Customer)-[:LIKES]->(category:Category) " +
            "WHERE id(customer) = $customerId " +
            "MATCH (recommendedActivity:AdditionalActivity)-[:BELONGS_TO]->(category) " +
            "MATCH (recommendedActivity)-[partOf:PART_OF]->(arr:Arrangement) " +
            "WHERE NOT (customer)-[:REGISTERED_FOR]->(recommendedActivity) " +
            "AND (customer)-[:BOOKED]->(arr) " +
            "RETURN DISTINCT recommendedActivity, partOf, arr")
    List<AdditionalActivity> recommendActivitiesByCategory(Long customerId);

    @Query("MATCH (customer:Customer)-[:BOOKED]->(arrangement:Arrangement)<-[:PART_OF]-(activity:AdditionalActivity) " +
            "WHERE id(customer) = $customerId " +
            "WITH customer, arrangement, AVG(activity.price) AS avgPrice " +
            "MATCH (recommendedActivity:AdditionalActivity)-[partOf:PART_OF]->(arrangement) " +
            "WHERE recommendedActivity.price < avgPrice " +
            "AND NOT (customer)-[:REGISTERED_FOR]->(recommendedActivity) " +
            "RETURN DISTINCT recommendedActivity, partOf, arrangement " +
            "ORDER BY recommendedActivity.price ASC")
    List<AdditionalActivity> findAffordableActivitiesForCustomer(Long customerId);

    @Query("MATCH (customer:Customer)-[:BOOKED]->(arr:Arrangement)<-[partOf:PART_OF]-(activity:AdditionalActivity) " +
            "WHERE id(customer) = $customerId " +
            "AND NOT (customer)-[:REGISTERED_FOR]->(activity) " +
            "OPTIONAL MATCH (:Customer)-[r:REGISTERED_FOR]->(activity) " +
            "WITH activity, partOf, arr, COALESCE(SUM(r.numberOfPeople), 0) AS registeredPeople " +
            "WHERE registeredPeople < activity.maxCapacity " +
            "WITH activity, partOf, arr, registeredPeople, toFloat(registeredPeople) / activity.maxCapacity AS occupancyRate " +
            "WHERE occupancyRate >= 0.7 " +
            "RETURN activity, partOf, arr " +
            "ORDER BY occupancyRate DESC, registeredPeople DESC")
    List<AdditionalActivity> findPopularActivitiesForCustomer(Long customerId);
}