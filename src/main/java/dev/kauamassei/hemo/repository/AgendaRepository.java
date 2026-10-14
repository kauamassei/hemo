package dev.kauamassei.hemo.repository;

import dev.kauamassei.hemo.model.AgendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<AgendaModel, Long> {
}
