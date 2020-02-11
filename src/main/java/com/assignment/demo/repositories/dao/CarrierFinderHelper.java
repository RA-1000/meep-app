/**
 *
 */
package com.assignment.demo.repositories.dao;

import com.assignment.demo.beans.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author dgaram
 *
 */
@Component
public interface CarrierFinderHelper extends JpaRepository<Carrier, Long> {

    /**
     * Finds one element by it's id
     *
     * @param id
     * @return
     */
    public Carrier findById(long id);

    /**
     * Finds one element by it's foreign id
     *
     * @param fid
     * @return
     */
    public Carrier findByFid(String fid);
}
