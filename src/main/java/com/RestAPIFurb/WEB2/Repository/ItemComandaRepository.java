package com.RestAPIFurb.WEB2.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RestAPIFurb.WEB2.Entity.ItemComanda;

@Repository
public interface ItemComandaRepository extends JpaRepository<ItemComanda, Long> {

    Optional<ItemComanda> findByComandaId(Long id);

}
