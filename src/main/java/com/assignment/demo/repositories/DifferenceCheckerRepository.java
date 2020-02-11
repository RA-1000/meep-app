/**
 *
 */
package com.assignment.demo.repositories;

import com.assignment.demo.beans.Carrier;
import com.assignment.demo.beans.Difference;
import com.assignment.demo.repositories.dao.DiferenceCheckerHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dgaram
 *
 */
@Repository
public class DifferenceCheckerRepository {
    private DiferenceCheckerHelper helper;

    private boolean empty = true;

    @Autowired
    public void setHelper(DiferenceCheckerHelper helper) {
        this.helper = helper;
    }

    public Difference retrieveDifference(String id) {
        return helper.findById(id);
    }

    public List<Difference> retrieveAllDifferences() {
        return helper.findAll();
    }

    /**
     * Simplified form of {@link DifferenceCheckerRepository#retrieveAllDifferencesByExist(boolean)} but expressed without parameters.
     * @return a list of Carriers id that no longer exist
     */
    public List<Difference> retrieveAllThatNoLongerExist() {
        return this.retrieveAllDifferencesByExist(false);
    }

    /**
     * Simplified form of {@link DifferenceCheckerRepository#retrieveAllDifferencesByExist(boolean)} but expressed without parameters.
     * @return a list of Carriers id that still available
     */
    public List<Difference> retrieveAllThatExist() {
        return this.retrieveAllDifferencesByExist(true);
    }

    public List<Difference> retrieveAllDifferencesByExist(boolean exist) {
        return helper.findByExist(exist);
    }

    public List<Difference> addDifference(List<Difference> ds) {
        return helper.saveAll(ds);
    }

    public boolean deleteAll() {
        helper.deleteAll();
        empty = helper.count() > 0;
        return empty;
    }
}
