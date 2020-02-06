package com.dsp.pojo;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@ToString
@Table("trimurl")
public class Trimmer implements Serializable {

    public Trimmer(){
    }

    @PrimaryKeyColumn(name = "partition", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int partition;

    @PrimaryKeyColumn(name = "id", ordinal =  1, type = PrimaryKeyType.CLUSTERED)
    private int id;

    @PrimaryKeyColumn(name = "create_time", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private long createTime;

    @PrimaryKeyColumn(name = "exp_time", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private long expTime;

    @Column(value = "long_url")
    private String longUrl;

    @Column(value = "short_url")
    private String shortUrl;

}
