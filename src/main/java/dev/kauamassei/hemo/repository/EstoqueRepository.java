package dev.kauamassei.hemo.repository;

import dev.kauamassei.hemo.model.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<EstoqueModel, Long> {
}
