package com.dropout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dropout.entity.FileDetails;

public interface FileDetailsRepository extends JpaRepository<FileDetails, Integer> {

}
