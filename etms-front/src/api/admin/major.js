import * as ajax from "../../common/ajax";

export const get = (number) => ajax.get("/admin/major/" + number);

export const create = (entity) => ajax.post("/admin/major", entity);

export const deleteItem = (number) => ajax.pureDelete("/admin/major/" + number);

export const update = (entity) => ajax.put("/admin/major", entity);

export const getPageCount = (departmentName, name) =>
  ajax.get("/admin/major/page/count", {
    departmentName: departmentName,
    name: name,
  });

export const getPage = (index, departmentName, name) =>
  ajax.get("/admin/major/page/" + index, {
    departmentName: departmentName,
    name: name,
  });

export const listName = () => ajax.pureGet("/admin/major/names");

export const pageSize = 20;
