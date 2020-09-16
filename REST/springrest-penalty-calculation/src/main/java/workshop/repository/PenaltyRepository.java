package workshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import workshop.entity.Trade;

@Repository
public interface PenaltyRepository extends JpaRepository<Trade, Integer> {

}
