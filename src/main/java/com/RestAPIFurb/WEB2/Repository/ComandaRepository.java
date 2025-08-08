package com.RestAPIFurb.WEB2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RestAPIFurb.WEB2.Entity.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

}
