<template>
    <div>
        <b-card-group columns>
            <b-card v-for="member in members" :key="member.id">
                <h2>{{ member.name }}</h2>
                <p>{{ member.email }}</p>
            </b-card>
        </b-card-group>
        <div>
            <b-button block variant="light" size="lg" v-b-modal.add-member>Create a Member</b-button>
            <b-modal id="add-member" ref="addMember" title="New Member" @ok="handleOk">
                <form ref="addProjectForm" @submit.stop.prevent="addMember">
                    <b-form-group label-cols-sm="4" label-cols-lg="3" label="Email" label-for="member-email"
                                  :state="form.state" invalid-feedback="Email address is required">
                        <b-form-input id="member-email" type="email" v-model="form.email" required></b-form-input>
                    </b-form-group>
                    <b-form-group label-cols-sm="4" label-cols-lg="3" label="Name" label-for="member-name"
                                  :state="form.state" invalid-feedback="Name is required">
                        <b-form-input id="member-name" type="text" v-model="form.name" required></b-form-input>
                    </b-form-group>
                    <b-form-group label-cols-sm="4" label-cols-lg="3" label="Password" label-for="member-password"
                                  :state="form.state" invalid-feedback="Password is required">
                        <b-form-input id="member-password" type="text" v-model="form.password" required></b-form-input>
                    </b-form-group>
                    <b-alert v-model="form.invalid" variant="danger" dismissible>
                        A valid email, members name and password is required!
                    </b-alert>
                </form>
            </b-modal>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                members: [],
                form: {
                    invalid: false,
                    email: '',
                    name: '',
                    password: ''
                }
            }
        },
        mounted() {
            this.loadMembers()
        },
        methods: {
            loadMembers() {
                this.axios.get('/web/member/list').then(response => {
                    if (response && response.status == 200) {
                        this.members = response.data.result.members
                    }
                }).catch(error => {
                    if (error.response) {
                        alert(error.response.data.message)
                    }
                })
            },
            handleOk(bvModalEvt) {
                // Prevent modal from closing
                bvModalEvt.preventDefault()
                // Trigger submit handler
                this.addMember()
            },
            addMember() {
                this.form.invalid = !this.$refs.addProjectForm.checkValidity()
                if (this.form.invalid) {
                    return
                }
                this.axios.post('/web/member/add', {
                    email: this.form.email,
                    name: this.form.name,
                    password: this.form.password
                }).then(response => {
                    console.log(response)
                    if (response && response.status == 200) {
                        this.$refs.addMember.hide()
                        this.loadMembers()
                    }
                }).catch(error => {
                    if (error.response) {
                        alert(error.response.data.message)
                    }
                })
            }
        }
    }
</script>