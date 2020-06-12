import * as ajax from "../../common/ajax";

export const get = (number) => ajax.get("/admin/course/" + number);

export const create = (entity) => ajax.post("/admin/course", entity);

export const deleteItem = (number) => ajax.pureDelete("/admin/course/" + number);

export const update = (entity) => ajax.put("/admin/course", entity);

export const getPageCount = (departmentName, name) =>
  ajax.get("/admin/course/page/count", {
    departmentName: departmentName,
    name: name,
  });

export const getPage = (index, departmentName, name) =>
  ajax.get("/admin/course/page/" + index, {
    departmentName: departmentName,
    name: name,
  });

export const listName = () => ajax.pureGet("/admin/course/names");

export const pageSize = 20;
