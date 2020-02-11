/**
 *
 */
package com.assignment.demo.repositories.dao;

import com.assignment.demo.beans.Difference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dgaram
 *
 */
@Component
public interface DiferenceCheckerHelper extends JpaRepository<Difference, Long> {

    /**
     * Finds one element by it's id
     *
     * @param id
     * @return
     */
    public Difference findById(String id);

    /**
     * Finds every element that has the exist field as true or false
     *
     * @param exist
     * @return
     */
    public List<Difference> findByExist(boolean exist);
}
