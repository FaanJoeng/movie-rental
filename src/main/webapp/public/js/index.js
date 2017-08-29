const movie = {
    //返回码常量
    returnCode: {
        //默认成功返回
        SUCCESS: () => 200,

        //登录成功
        SIGNIN_SUCCESS: () => 210,

        //注销成功
        SIGNOUT_SUCCESS: () => 211,

        //获取客户列表成功
        GET_CUSTOMER_LIST_SUCCESS: () => 220,

        //新增客户成功
        ADD_CUSTOMER_SUCCESS: () => 221,

        //更新客户成功
        UPDATE_CUSTOMER_SUCCESS: () => 222,

        //删除客户成功
        DELETE_CUSTOMER_SUCCESS: () => 223,

        //地址列表获取成功
        GET_ADDRESS_LIST_SUCCESS: () => 230,

        //默认失败返回
        FAILURE: () => 300,

        //用户不存在
        USER_NOT_EXIST_ERROR: () => 301,

        //用户名错误
        USER_NAME_ERROR: () => 311,

        //注销失败
        SIGNOUT_FAILURE: () => 312,

        //客户列表为空
        CUSTOMER_LIST_EMPTY: () => 320,

        //新增客户失败
        ADD_CUSTOMER_FAILURE: () => 321,

        //更新客户失败
        UPDATE_CUSTOMER_FAILURE: () => 322,

        //删除客户失败
        DELETE_CUSTOMER_FAILURE: () => 323,

        //地址列表获取失败
        GET_ADDRESS_LIST_FAILURE: () => 330,

        //服务器异常
        SERVER_ERROR: () => 400
    },

    //保存变量
    variable: {
        totalPages: undefined,
        currentPage: undefined
    },

    //URL存放
    url: {
        customers: () => "customers",
        session: () => "session",
        address: () => "address"
    },

    /**
     * 项目通用函数
     */
    common: {
        /**
         * 将数据渲染进模板并将生成的html插入DOM
         * @param temp 模板ID
         * @param data 数据对象
         * @param position 目的节点ID
         */
        render: (temp, data, position) => {
            //将ArtTemplate的界定符从%设置为？防止与jsp冲突
            let rule = template.defaults.rules[0]
            rule.test = new RegExp(rule.test.source.replace('<%', '<\\?').replace('%>', '\\?>'))

            //html 为数据渲染进模板后的内容
            const html = template(temp, data)

            //将渲染后的内容插入DOM
            document.getElementById(position).innerHTML = html
        },


        /**
         * 表单校验函数
         * @param ele 表单ID
         * @return
         */
        validateForm: (ele) => {
            // firstName及lastName均为1-10位字母或数字或下划线组合
            const regName = /^[\w]{1,10}$/
            //验证是否为邮箱或者为空
            const regEmail = /^(([a-zA-Z0-9_.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+)?$/

            //从页面获取firstName, lastName, email的值
            let firstName = $("#" + ele + " input[name=firstName]").val()
            let lastName = $("#" + ele + " input[name=lastName]").val()
            let email = $("#" + ele + " input[name=email]").val()

            //校验firstName
            if (!regName.test(firstName)) {
                movie.common.showValidateMsg("#" + ele + " input[name=firstName]", "error", "FirstName 为1-10位字母或数字或下划线组合")
                return false
            } else {
                movie.common.showValidateMsg("#" + ele + " input[name=firstName]", "success", "")
            }

            //校验lastName
            if (!regName.test(lastName)) {
                movie.common.showValidateMsg("#" + ele + " input[name=lastName]", "error", "LastName 为1-10位字母或数字或下划线组合")
                return false
            } else {
                movie.common.showValidateMsg("#" + ele + " input[name=lastName]", "success", "")
            }

            //校验email
            if (!regEmail.test(email)) {
                movie.common.showValidateMsg("#" + ele + " input[name=email]", "error", "邮箱格式不正确")
                return false
            } else {
                movie.common.showValidateMsg("#" + ele + " input[name=email]", "success", "")
            }

            return true
        },


        /**
         * 表单校验时显示校验信息
         * @param ele 校验表单域ID
         * @param status 校验状态
         * @param msg 校验信息
         */
        showValidateMsg: (ele, status, msg) => {
            //清楚表单校验遗留样式及提示信息
            $(ele).parent().removeClass("has-success has-error")
            $(ele).next("span").text(" ")

            if ("success" == status) {
                $(ele).parent().addClass("has-success")
                $(ele).next("span").text(msg)
            } else if ("error" == status) {
                $(ele).parent().addClass("has-error")
                $(ele).next("span").text(msg)
            }
        },

        /**
         * 重置表单
         * @param ele 要充值表单的ID
         * @param total 是否清除表单的内容 true 清除内容及后添加的样式 false 仅清除后添加的样式
         * @return
         */
        resetForm: (ele, total) => {
            if (total) {
                $(ele)[0].reset()
            }
            $(ele).find("*").removeClass("has-error has-success")
            $(ele).find(".help-block").text("")
        }
    },

    /*主页相关操作*/
    customer: {
        //主页初始化，在主页加载时执行
        init: () => {
            //Ajax获取customer信息并初始化信息列表
            movie.customer.getCustomerWithJson()

            //绑定客户添加保存按钮点击事件
            movie.customer.saveAddCustomer()

            //绑定客户更新保存按钮点击事件
            movie.customer.saveUpdateCustomer()

            //新增客户按钮绑定触发模态框点击事件
            $("#addCustomerBtn").click(() => {
                movie.common.resetForm("#addCustomerForm", true)

                //获取地址列表并填充
                movie.customer.getAddress("#addCustomerForm select")
                $("#addCustomerModal").modal({
                    backdrop: "static"
                })
            })

            //给各个客户信息编辑按钮绑定模态框触发事件
            $(document).on("click", ".customerEditBtn", function () {
                movie.common.resetForm("#updateCustomerForm", true)

                movie.customer.getAddress("#updateCustomerForm select", $(this).attr("data-customer-id"))

                //给更新保存按钮绑定data-customer-id属性，在提交表单式作为参数使用
                $("#updateCustomerSaveBtn").attr("data-customer-id", $(this).attr("data-customer-id"))
                $("#updateCustomerModal").modal({
                    backdrop: "static"
                })
            })

            //给注销按钮绑定单击事件
            $("#signOutBtn").click(() => {
                $.ajax({
                    url : movie.url.session(),
                    type : "delete",
                    dataType : "json",
                    success : ({meta}) => {
                        if(meta.code == movie.returnCode.SIGNOUT_SUCCESS()){
                            alert(meta.msg)
                            window.location.href = "session";
                        } else {
                            alert(meta.msg)
                            window.location.reload()
                        }
                    }
                })
            })

            //绑定Customer、Film面板切换事件
            movie.customer.switchPanel()

            //给各个客户信息删除按钮绑定删除事件
            $(document).on("click", ".customerDeleteBtn", function () {
                let customerId = $(this).attr("data-customer-id")
                let firstName = $(this).parents("tr").find("td:eq(2)").text()

                if (confirm("确认删除【" + firstName + "】吗?")) {
                    $.ajax({
                        url: movie.url.customers() + "/" + customerId,
                        type: "delete",
                        dataType: "json",
                        success: ({meta}) => {
                            if (meta.code == movie.returnCode.DELETE_CUSTOMER_SUCCESS()) {
                                alert(meta.msg)
                                movie.customer.getCustomerWithJson(movie.variable.currentPage)
                            } else {
                                alert(meta.msg)
                                window.location.reload()
                            }
                        }
                    })
                }
            })


            //以下为多选删除相关事件
            $(document).on("click", ".check_item", () => {
                let flag = $(".check_item:checked").length == $(".check_item").length
                $("#check_all").prop("checked", flag)
            })

            $(document).on("click", "#check_all", function () {
                $(".check_item").prop("checked", $(this).prop("checked"))
            })

            $("#deleteAllBtn").click(function () {
                let names = ""
                let ids = ""

                //获取要删除客户信息的firstName及ID
                $(".check_item:checked").each(function () {
                    names += $(this).parents("tr").find("td:eq(2)").text() + ","
                    ids += $(this).parents("tr").find("td:eq(1)").text() + "-"
                })

                names = names.substr(0, names.length - 1)
                ids = ids.substr(0, ids.length - 1)

                if (confirm("确认删除【" + names + "】吗？")) {
                    $.ajax({
                        url: movie.url.customers() + "/" + ids,
                        type: "delete",
                        dataType: "json",
                        success: ({meta, data}) => {
                            if (meta.code == movie.returnCode.DELETE_CUSTOMER_SUCCESS()) {
                                alert("成功删除" + data.sum + "条信息！")
                                movie.customer.getCustomerWithJson(movie.variable.currentPage)
                            } else {
                                alert(meta.msg)
                                window.location.reload()
                            }
                        }
                    })
                }
            })
        },

        //切换客户管理及电影设置
        switchPanel : () => {
            let customerBtn = $("#mg_customer")
            let filmBtn = $("#set_film")

            let customerPanel =$("#index_customer")
            let filmPanel = $("#index_film")

            customerBtn.click(() => {
                if(customerPanel.display = "none"){
                    customerPanel.css("display", "block")
                }
                if(filmPanel.display = "block"){
                    filmPanel.css("display", "none")
                }
            })

            filmBtn.click(() => {
                if(filmPanel.display = "none"){
                    filmPanel.css("display", "block")
                }

                if(customerPanel.display = "block"){
                    customerPanel.css("display", "none")
                }
            })

        },

        /**
         * 根据客户ID查询客户信息
         * @param id 要要查询的客户ID
         */
        getCustomerById: (id) => {
            $.ajax({
                url: movie.url.customers() + "/" + id,
                type: "get",
                dataType: "json",
                success: ({meta, data}) => {
                    if (meta.code == movie.returnCode.SUCCESS()) {
                        //将获取的客户信息填充进客户信息更新表单
                        $("#updateCustomerFirstNameInput").val(data.customer.firstName)
                        $("#updateCustomerLastNameInput").val(data.customer.lastName)
                        $("#updateCustomerEmailInput").val(data.customer.email)
                        $("#updateCustomerForm select").val([data.customer.addId])
                    } else {
                        alert(meta.msg)
                        window.location.reload()
                    }
                }
            })
        },

        /**
         * 保存新增客户表单
         */
        saveAddCustomer: () => {
            $("#addCustomerSaveBtn").click(() => {

                //表单信息前端校验不成功
                if (!movie.common.validateForm("addCustomerForm")) {
                    return false
                }

                $.ajax({
                    url: movie.url.customers(),
                    type: "post",
                    data: $("#addCustomerForm").serialize(),
                    dataType: "json",
                    success: ({meta, data}) => {
                        if (meta.code == movie.returnCode.ADD_CUSTOMER_SUCCESS()) {
                            $("#addCustomerModal").modal("hide")
                            movie.customer.getCustomerWithJson(movie.variable.totalPages)
                        } else {
                            //后端校验失败，将错误信息反馈给前端
                            movie.common.resetForm("#addCustomerForm", false)
                            if (undefined != data.errorFields.firstName) {
                                movie.common.showValidateMsg("#addCustomerForm input[name=firstName]", "error", data.errorFields.firstName)
                            }

                            if (undefined != data.errorFields.lastName) {
                                movie.common.showValidateMsg("#addCustomerForm input[name=lastName]", "error", data.errorFields.lastName)
                            }

                            if (undefined != data.errorFields.email) {
                                movie.common.showValidateMsg("#addCustomerForm input[name=email]", "error", data.errorFields.email)
                            }
                        }
                    }
                })
            })
        },

        /**
         * 保存更新客户表单
         */
        saveUpdateCustomer: () => {
            $("#updateCustomerSaveBtn").click(function () {
                if (!movie.common.validateForm("updateCustomerForm")) {
                    return false
                }

                $.ajax({
                    url: movie.url.customers() + "/" + $(this).attr("data-customer-id"),
                    type: "put",
                    data: $("#updateCustomerForm").serialize(),
                    dataType: "json",
                    success: ({meta}) => {
                        if (meta.code == movie.returnCode.UPDATE_CUSTOMER_SUCCESS()) {
                            $("#updateCustomerModal").modal("hide")
                            movie.customer.getCustomerWithJson(movie.variable.currentPage)
                        } else {
                            //后端校验失败，将错误信息反馈给前端
                            movie.common.resetForm("#updateCustomerForm", false)
                            if (undefined != data.errorFields.firstName) {
                                movie.common.showValidateMsg("#updateCustomerForm input[name=firstName]", "error", data.errorFields.firstName)
                            }

                            if (undefined != data.errorFields.lastName) {
                                movie.common.showValidateMsg("#updateCustomerForm input[name=lastName]", "error", data.errorFields.lastName)
                            }

                            if (undefined != data.errorFields.email) {
                                movie.common.showValidateMsg("#updateCustomerForm input[name=email]", "error", data.errorFields.email)
                            }
                        }
                    }
                })
            })
        },


        /**
         * 根据页码书获取JSON格式的Customer信息
         * @param pn 获取的页
         */
        getCustomerWithJson: (pn) => {
            $.ajax({
                url: movie.url.customers(),
                type: "get",
                dataType: "json",
                data: {
                    pn: pn
                },
                success: ({meta, data}) => {
                    if (meta.code == movie.returnCode.GET_CUSTOMER_LIST_SUCCESS()) {
                        //清除全选删除的选定状态
                        $("#check_all").prop("checked", false)

                        //这个for循环用来格式化日期输出
                        for(let i = 0; i < data.page.size; i++){
                            var date = new Date()
                            date.setTime(data.page.list[i].lastUpdate)
                            data.page.list[i].lastUpdate = date.getFullYear() + "-" +
                                                           (date.getMonth() + 1) + "-" +
                                                           date.getDate() + " " +
                                                           date.getHours() + ":" +
                                                           date.getMinutes() + ":" +
                                                           date.getSeconds()
                        }

                        /**
                         * 如果成功拿到数据，通过template()函数，将数据分别填充进客户信息
                         * 表格模板、分页信息模板、分页链接模板，并插入相关节点下
                         */
                        movie.common.render("customer_table_template", data.page, "customer_table")
                        movie.common.render("pagination_info_template", data.page, "pagination_info")
                        movie.common.render("pagination_template", data.page, "pagination")

                        /**
                         * 将数据总数及当前页保存到movie对象的variable属性
                         * 的totalPages,currentPage里
                         */
                        movie.variable.totalPages = data.page.pages
                        movie.variable.currentPage = data.page.pageNum

                        /**
                         * 下面的事件分别绑定到分页连接上，点击按钮 从服务器取回
                         * 对应的数据并与模板结合，再插入DOM
                         */
                        $("#first_page").click(() => {
                            movie.customer.getCustomerWithJson(1)
                        })

                        $("#last_page").click(() => {
                            movie.customer.getCustomerWithJson(data.page.pages)
                        })

                        $("#pre_page").click(() => {
                            movie.customer.getCustomerWithJson(data.page.pageNum - 1)
                        })

                        $("#next_page").click(() => {
                            movie.customer.getCustomerWithJson(data.page.pageNum + 1)
                        })

                        $(".target_page").each(function () {
                            $(this).click(() => {
                                movie.customer.getCustomerWithJson($(this).text())
                            })
                        })

                    } else {
                        //没有拿到数据则弹出返回信息，并刷新当前页
                        alert(meta.msg)
                        window.location.reload()
                    }

                }
            })
        },

        /**
         * 获取地址列表JSON并插入DOM
         * @param ele 插入节点的ID
         * @param customerId 要更新customerID
         */
        getAddress: (ele, customerId) => {
            $.getJSON(movie.url.address(), ({meta, data}) => {
                if (meta.code == movie.returnCode.GET_ADDRESS_LIST_SUCCESS()) {
                    let str = ""
                    let {list} = data

                    for (let i = 0; i < list.length; i++) {
                        str += `<option value=${list[i].addressId}>${list[i].address}</option>`
                    }

                    $(ele).html(str)

                    /**
                     * 从后台获取客户信息并填充进表单，确保信息的实时性
                     * 确保内容填充在拿回地址列表后执行
                     */
                    if(customerId){
                        movie.customer.getCustomerById(customerId)
                    }
                }
            })
        }
    },


    /*用户登录页相关操作*/
    session: {
        //在用户登录加载完成时执行，为登录表单绑定提交事件
        init: () => {
            $("#signInForm button").on("click", () => {
                $.post({
                    url: movie.url.session(),
                    data: $("#signInForm").serialize(),
                    dataType: "json",
                    success: ({meta}) => {
                        if (meta.code == movie.returnCode.SIGNIN_SUCCESS()) {
                            //获取项目上下文路径
                            let pathName = window.location.pathname
                            let rootPath = pathName.substring(0, pathName.substr(1).indexOf("/") + 1)
                            window.location.href = rootPath
                        } else {
                            alert(meta.msg)
                            window.location.reload()
                        }
                    }
                })
            })
        }
    }
}
