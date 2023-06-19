package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @version 1.0
 * @Author lianglei
 * @Date: 2023/06/19/ 15:27
 * @description 后台用户角色管理controller控制器
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/role")
@Api(tags = "UmsRoleController")
@Tag(name = "UmsRoleController", description = "后台用户角色管理")
public class UmsRoleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleController.class);

    @Autowired
    private UmsRoleService umsRoleService;


    /**
     * 获取所有的角色
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<UmsRole>> listAll() {
        List<UmsRole> umsRoleList = umsRoleService.listAll();
        return CommonResult.success(umsRoleList);
    }

    /**
     * 添加角色
     *
     * @param umsRole
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsRole umsRole) {
        int count = umsRoleService.create(umsRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("添加失败！");
    }

    /**
     * 获取指定角色信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取指定角色信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<UmsRole> getItem(@PathVariable Long id) {
        return CommonResult.success(umsRoleService.getItem(id));
    }

    /**
     * 修改角色
     *
     * @param id
     * @param umsRole
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsRole umsRole) {
        int count = umsRoleService.update(id, umsRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("更新用户信息失败！");
    }

    /**
     * 根据角色名称分页获取角色列表
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                  @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsRole> umsRoleList = umsRoleService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(umsRoleList));
    }


    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids) {
        int count = umsRoleService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("删除失败，请稍后重试！");
    }


    /**
     * 修改角色状态
     *
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "修改角色状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = umsRoleService.update(id, umsRole);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed("修改角色状态失败！");
    }

    /**
     * 获取角色相关菜单
     *
     * @param roleId
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "获取角色相关菜单")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.POST)
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long roleId) {
        List<UmsMenu> umsMenuList = umsRoleService.listMenu(roleId);
        return CommonResult.success(umsMenuList);
    }


}
