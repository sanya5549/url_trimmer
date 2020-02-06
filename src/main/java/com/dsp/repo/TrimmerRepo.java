package com.dsp.repo;

import com.dsp.pojo.Trimmer;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @desc: Repository corresponding to the trimurl table in cassandra'
 */

@Repository
public interface TrimmerRepo extends CrudRepository<Trimmer, String> {
    @Query("select * from trimmer.trimurl where partition = ?0 and id = ?1")
    List<Trimmer> findByPrimaryKey(int partition, int id);
}
