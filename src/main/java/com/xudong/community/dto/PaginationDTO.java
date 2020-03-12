package com.xudong.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxudong on 2020-03-04 18:26
 */
@Data
public class PaginationDTO {

    private List<QuestionDTO> questionDTOS;
    private Boolean showPrevious; //是否有向前按钮
    private Boolean showFirstPage;
    private Boolean showNext;
    private Boolean showEndPage;

    private Integer page;

    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;


    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }
        // 是否展示第一页
        if (pages.contains(1)) { //contains()检测某个节点是不是另一个节点的后代,是后代节点返回true;否则返回false
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
