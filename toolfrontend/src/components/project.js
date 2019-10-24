import React, { Component } from 'react';
import Axios from 'axios';
import Background from '../images/addproj.png';
import swal from 'sweetalert';


const Data = props => (
    <tr>
        {/* <td>{props.id}</td> */}

        <th>{props.line}</th>
        <th>{props.cs}</th>
        <th>{props.cnc}</th>
        <th>{props.ctc}</th>
        <th>{props.ci}</th>
        <th>{props.tw}</th>
        <th>{props.cps}</th>
        <th>{props.cr}</th>
        {/* <td><input type="button" value="Join" className="btn btn-primary" onClick={props.onClick} id={props.id} /></td> */}
        {/* <td>
            <Link to={"/books/" + props._id}>Book</Link>
        </td>
        <td>
            <Link to={"/books/" + props._id}>Book</Link>
        </td> */}
    </tr>
);

export default class Home extends Component {

    constructor(props) {
        super(props);

        this.onSubmit = this.onSubmit.bind(this);
        this.onChangeProjectName = this.onChangeProjectName.bind(this);

        this.state = {
            projectName: null
        }

    }

    componentDidMount() {
        document.title = "Code Complexity Tool";

    }

    onChangeProjectName(e){

        this.setState({
            projectName: e.target.value
        });


    }









    onSubmit(e) {
        e.preventDefault();


        

        
        if (this.state.projectName !== '' && this.state.projectName !== null) {

            const project = {
                projectName: this.state.projectName
            };



            Axios.post('http://localhost:8080/project/add/', project)
                        .then(result => {

                            console.log(result);

                            this.state = {
                                projectName: null
                            }

                            swal("Success !", "Project Added Sucessfully !", "success");
                            //after success signup go to this login route and load the login component 
                            this.props.history.push("/");

                        }).catch(error => {
                            console.log(error);
                            this.setState({
                                msg: 'Something Went Wrong'
                            });
                            swal("Failed !", "Server is not responding", "error");
                        });

        }else{
            swal("Failed !", "Please Enter Project Name", "warning");
        }

       

       
    }




    render() {
        return (
           
           


            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-xl-10 col-lg-10 col-md-10">
                        <div className="card o-hidden border-0 shadow-lg my-5">
                            <div className="card-body p-0">
                                <div className="row">
                                    <div className="col-lg-4">
                                        <img src={Background} style={{ margin: '50px', width: '300px' }} alt="IMG" />
                                    </div>
                                    <div className="col-lg-8">
                                        <div className="p-5">
                                            <div className="text-center">
                                                <h1 className="h4 text-gray-900 mb-4">Add A New Project</h1>
                                                <hr />
                                            </div>
                                            <hr />
                                            <form className="project">
                                                <div className="form-group">
                                                    <input value={this.state.projectName} onChange={this.onChangeProjectName} type="text" className="form-control form-control-user" placeholder="Project Name" />
                                                </div>
                                               
                                                <a href="index.html" className="btn btn-primary btn-user btn-block" onClick={this.onSubmit}>Add Project</a>
                                            </form>
                                            <hr />

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>

            </div>


        )
    }
}