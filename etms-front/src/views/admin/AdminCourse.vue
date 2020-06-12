<template>
  <div class="course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 课程管理
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :span="2">
            <el-button @click="create" icon="el-icon-plus">创建</el-button>
          </el-col>
          <el-col :offset="10" :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="课程名"
              v-model="queryForm.name"
            />
          </el-col>
          <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="学院名"
              v-model="queryForm.departmentName"
            />
          </el-col>
          <el-col :span="3">
            <el-button @click="query" icon="el-icon-search" type="primary"
              >搜索
            </el-button>
          </el-col>
        </el-row>
      </div>

      <el-row justify="center" type="flex">
        <el-pagination
          :current-page.sync="pageIndex"
          :page-size="pageSize"
          :total="pageSize * pageCount"
          @current-change="getPage"
          background
          layout="prev, pager, next"
        >
        </el-pagination>
      </el-row>

      <div class="table">
        <el-table :data="tableData" stripe>
<!--          <el-table-column label="课程Id" prop="id" />-->
          <el-table-column label="课程号" prop="number" />
          <el-table-column label="课程名" prop="name" width="200px" />
          <el-table-column label="授课年级" prop="grade" />
          <el-table-column label="学分" prop="credit" />
          <el-table-column label="学时" prop="time" />
          <el-table-column label="所属学院" prop="departmentName" />
          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button @click="edit(scope.row.number)" size="mini" type="success"
                >编辑
              </el-button>
              <el-button
                @click="deleteItem(scope.row.number)"
                size="mini"
                type="danger"
                >删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

      <el-dialog :visible.sync="editing" title="编辑" width="30%">
        <el-form :model="entityForm" label-width="70px" ref="form">
            <el-form-item label="课程号">
                <el-input v-model="entityForm.number"></el-input>
            </el-form-item>
          <el-form-item label="课程名">
            <el-input v-model="entityForm.name"></el-input>
          </el-form-item>
          <el-form-item label="授课年级">
            <el-input type="number" v-model="entityForm.grade"></el-input>
          </el-form-item>
          <el-form-item label="学分">
            <el-input type="number" v-model="entityForm.credit"></el-input>
          </el-form-item>
          <el-form-item label="学时">
             <el-input type="number" v-model="entityForm.time"></el-input>
           </el-form-item>
           <el-form-item label="所属学院">
              <el-select placeholder="请选择学院" v-model="entityForm.departmentId">
                 <el-option
                        :key="index"
                        :label="item.name"
                        :value="item.id"
                        v-for="(item, index) in departments"
                />
                <!--              label 才是真正的值-->
            </el-select>
            </el-form-item>
        </el-form>
        <span class="dialog-footer" slot="footer">
          <el-button @click="save" type="primary">确 定</el-button>
          <el-button @click="editing = false">取 消</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/admin/course";
import * as teacherApi from "../../api/admin/teacher";
import * as departmentApi from "../../api/admin/department";

export default {
  name: "AdminCourse",
  data() {
    return {
      queryForm: {
        departmentName: "",
        name: "",
      },
      entityForm: {},
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false,
      departments: [],
    };
  },
  methods: {
    query() {
      api
        .getPageCount(
          this.queryForm.departmentName,
          this.queryForm.name
        )
        .then((res) => {
          this.pageCount = res;
          this.pageIndex = 1;
          this.getPage(1);
        });
    },
    getPage(pageIndex) {
      api
        .getPage(
          pageIndex,
          this.queryForm.departmentName,
          this.queryForm.name
        )
        .then((res) => {
          this.tableData = res;
        });
    },
    create() {
      this.entityForm = {
        id: -1,
        number: "",
        name: "",
        grade: 1,
        credit: 2,
        time: 20,
        departmentId:"",
      };
      this.editing = true;
    },
    edit(number) {
      api.get(number).then((res) => {
        this.entityForm = res;
        this.editing = true;
      });
    },
    save() {
      if (this.entityForm.id === -1) {
        api.create(this.entityForm).then(() => {
          this.finishSave();
          this.entityForm.id = -1;
        });
      } else {
        api.update(this.entityForm).then(() => {
          this.finishSave();
        });
      }
    },
    finishSave() {
      this.$message.success("成功");
      this.getPage(this.pageIndex);
      this.editing = false;
    },
    deleteItem(number) {
      api.deleteItem(number).then(() => {
        this.$message.success("删除成功");
        this.getPage(this.pageIndex);
      });
    },
    getDepartments() {
      departmentApi.listName().then((res) => {
        this.departments = res;
      });
    },
  },
  created() {
    this.query();
    this.getDepartments();
  },
};
</script>

<style scoped></style>
