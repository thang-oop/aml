package vn.com.pvcombank.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.apache.commons.compress.utils.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.domain.BLUploadFile;
import vn.com.pvcombank.repository.BLUploadFileRepository;

/**
 * Service Implementation for managing {@link BLUploadFile}.
 */
@Service
@Transactional
public class BLUploadFileService {

    private final Logger log = LoggerFactory.getLogger(BLUploadFileService.class);

    private final BLUploadFileRepository bLUploadFileRepository;

    public BLUploadFileService(BLUploadFileRepository bLUploadFileRepository) {
        this.bLUploadFileRepository = bLUploadFileRepository;
    }

    @Autowired
    BLUploadFileConvertService testService;

    @PostConstruct
    public void test() {
        try {
            final String src = "D:\\PVCB\\AML\\06DS\\AML-DS den.xlsx";
            System.out.println("converting...: " + src);

            final Map<String, String> mapRaw = new HashMap<>() {
                {
                    put("FULL.NAME.BL", "HỌ VÀ TÊN ĐẦY ĐỦ^TÊN KHÁC^TÊN KHÁC 2^TÊN KHÁC 3");
                    put("LEGAL.ID", "SỐ GIẤY TỜ PHÁP LÝ 1");
                }
            };
            final Map<String, String[]> map = new HashMap<>() {
                {
                    mapRaw.forEach((k, v) -> put(k, v.split("\\^")));
                }
            };

            final InputStream contents = testService.convert(new FileInputStream(src), map);

            final String des = "D:\\PVCB\\AML\\test.csv";

            Files.copy(contents, new File(des).toPath(), StandardCopyOption.REPLACE_EXISTING);

            IOUtils.closeQuietly(contents);
            System.out.println("convert success: " + des);
        } catch (IOException e) {
            System.out.println("convert IOException: " + e);
        }
    }

    /**
     * Save a bLUploadFile.
     *
     * @param bLUploadFile the entity to save.
     * @return the persisted entity.
     */
    public BLUploadFile save(BLUploadFile bLUploadFile) {
        log.debug("Request to save BLUploadFile : {}", bLUploadFile);
        return bLUploadFileRepository.save(bLUploadFile);
    }

    /**
     * Partially update a bLUploadFile.
     *
     * @param bLUploadFile the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<BLUploadFile> partialUpdate(BLUploadFile bLUploadFile) {
        log.debug("Request to partially update BLUploadFile : {}", bLUploadFile);

        return bLUploadFileRepository
            .findById(bLUploadFile.getId())
            .map(
                existingBLUploadFile -> {
                    if (bLUploadFile.getSourceFileName() != null) {
                        existingBLUploadFile.setSourceFileName(bLUploadFile.getSourceFileName());
                    }
                    if (bLUploadFile.getSystemFileName() != null) {
                        existingBLUploadFile.setSystemFileName(bLUploadFile.getSystemFileName());
                    }
                    if (bLUploadFile.getTagetCompany() != null) {
                        existingBLUploadFile.setTagetCompany(bLUploadFile.getTagetCompany());
                    }
                    if (bLUploadFile.getValidate() != null) {
                        existingBLUploadFile.setValidate(bLUploadFile.getValidate());
                    }
                    if (bLUploadFile.getServiceStatus() != null) {
                        existingBLUploadFile.setServiceStatus(bLUploadFile.getServiceStatus());
                    }
                    if (bLUploadFile.getSystemFileSize() != null) {
                        existingBLUploadFile.setSystemFileSize(bLUploadFile.getSystemFileSize());
                    }
                    if (bLUploadFile.getRecordStatus() != null) {
                        existingBLUploadFile.setRecordStatus(bLUploadFile.getRecordStatus());
                    }
                    if (bLUploadFile.getUploadBy() != null) {
                        existingBLUploadFile.setUploadBy(bLUploadFile.getUploadBy());
                    }
                    if (bLUploadFile.getDateUpload() != null) {
                        existingBLUploadFile.setDateUpload(bLUploadFile.getDateUpload());
                    }
                    if (bLUploadFile.getAuthoriseBy() != null) {
                        existingBLUploadFile.setAuthoriseBy(bLUploadFile.getAuthoriseBy());
                    }
                    if (bLUploadFile.getDateAuthorise() != null) {
                        existingBLUploadFile.setDateAuthorise(bLUploadFile.getDateAuthorise());
                    }

                    return existingBLUploadFile;
                }
            )
            .map(bLUploadFileRepository::save);
    }

    /**
     * Get all the bLUploadFiles.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<BLUploadFile> findAll(Pageable pageable) {
        log.debug("Request to get all BLUploadFiles");
        return bLUploadFileRepository.findAll(pageable);
    }

    /**
     * Get one bLUploadFile by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<BLUploadFile> findOne(Long id) {
        log.debug("Request to get BLUploadFile : {}", id);
        return bLUploadFileRepository.findById(id);
    }

    /**
     * Delete the bLUploadFile by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete BLUploadFile : {}", id);
        bLUploadFileRepository.deleteById(id);
    }
}
