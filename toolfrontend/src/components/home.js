import React, { Component } from 'react';
import Axios from 'axios';


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


const Data2 = props => (
  
    <tr>
        {/* <td>{props.id}</td> */}


        
            <th>{props.line}</th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>

            
        
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
        this.onChangeFile = this.onChangeFile.bind(this);
        this.onChangeFilePath = this.onChangeFilePath.bind(this);

        this.state = {
            fileName: 'Choose File',
            file: null,
            filePath: null,
            fullPath: null,
            dataArray: [],
            projects:[],
            totalComplexity:0
        }

    }

    componentDidMount() {
        document.title = "Code Complexity Tool";

    }


    submit() {
        if (this.state.file == null) {
            return (
                <input type="submit" className='btn btn-success uploadButton' value="Upload" disabled />
            )
        } else {
            if (this.state.filePath == null) {
                return (
                    <input type="submit" className='btn btn-success uploadButton' value="Upload" disabled />
                )
            } else {
                return (
                    <input type="submit" className='btn btn-success uploadButton' value="Upload" />
                )
            }

        }
    }

    onChangeFilePath(e) {


        this.setState({
            filePath: e.target.value
        });
    }


    onChangeFile(e) {


        if (e.target.files[0] == null) {
            this.setState({
                fileName: 'Choose File'
            });
        } else {
            const fileX = e.target.files[0];
            console.log(fileX);
            this.setState({
                file: e.target.files[0],
                fileName: e.target.files[0].name
            });
        }

    }


    onSubmit(e) {
        e.preventDefault();

        this.setState({
            fullPath: this.state.filePath + "\\" + this.state.fileName
        });

        const fd = new FormData();
        fd.append("file", this.state.file, this.state.fullPath);


        console.log(this.state.file);
        console.log(this.state.file.name);

        Axios.post('http://localhost:8080/file/upload', fd).then(resolve => {




            this.setState({
                dataArray: resolve.data
            });


            console.log(this.state.dataArray);


            let total=0;
            for(let i=0;i<this.state.dataArray.length;i++){
                

                let x = resolve.data[i].cr;
                let y = this.state.dataArray[i].cps;

                
                if(x > 0){
                    total+=x;
                    console.log(x);
                }else{
                    total+=y;
                }
            }

            console.log("scdsd"+total);

            this.state.totalComplexity = total;
            this.props.history.push('/');
            // let today = new Date();
            // let dd = String(today.getDate()).padStart(2, '0');
            // let mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
            // let yyyy = today.getFullYear();

            // today = yyyy + '-' + mm + '-' + dd;
            // if (this.state.submissionFile != null) {
            //     const obj = {
            //         file: resolve.data,
            //         date: today
            //     }
            //     Axios.put('http://localhost:4000/node/submission/' + this.state.subId, obj).then(resolve => {
            //         console.log(resolve);
            //         this.props.history.push('/');
            //     }).catch(err => {
            //         console.log(err);
            //     });
            // } else {
            //     console.log(resolve)

            //     console.log(today);
            //     const obj = {
            //         assId: this.state.assignmentID,
            //         stdId: sessionStorage.getItem('UserID'),
            //         file: resolve.data,
            //         date: today
            //     };
            //     Axios.post('http://localhost:4000/node/submission', obj).then(resolve => {
            //         console.log(resolve);
            //         this.props.history.push('/');
            //     }).catch(err => {
            //         console.log(err);
            //     });
            // }


        }).catch(err => {
            console.log(err)
        });
    }


    displayTable(currentData,i) {
        
        if (currentData.cps == 0) {
          return <Data2 line={currentData.line} key={i} />;
        }else{
            return <Data line={currentData.line} key={i} ci={currentData.ci} cnc={currentData.cnc} cps={currentData.cps} cr={currentData.cr}
            cs={currentData.cs} ctc={currentData.ctc} tw={currentData.tw} />;
        }
      }



    render() {
        return (



            <div className="container-fluid">
                <div className="row justify-content-center">
                    <div className="col-lg-12">
                        <div className="card o-hidden border-0 shadow-lg my-5">
                            <div className="card-body p-0">
                                <div className="row">

                                    <div className="col-lg-12">
                                        <div className="p-5">


                                            <form className="file" onSubmit={this.onSubmit}>

                                                <div className='card-body'>


                                                
                                                    <div className="text-center">
                                                        <h1 className="h4 text-gray-900 mb-4">Calculate Complexity</h1>
                                                        <hr />
                                                    </div>

                                                    {/* <div class="form-group">

                                                        <div class="input-group   mb-3  col-md-5">

                                                            <select className="form-control">
                                                                <option value="grapefruit">Select Project</option>

                                                            </select>
                                                        </div>
                                                    </div> */}

                                                    <div className="form-group">

                                                        <div className="input-group  mb-3  col-md-5">
                                                            <div className="custom-file">
                                                                <input type="file" required className="custom-file-input" id="inputGroupFile01"
                                                                    onChange={this.onChangeFile} />
                                                                <label className="custom-file-label"
                                                                    htmlFor="inputGroupFile01">{this.state.fileName}</label>
                                                            </div>
                                                        </div>
                                                    </div>


                                                    <div className="form-group">
                                                        <div className="input-group  mb-3  col-md-5">
                                                            <input type="text" className="form-control" id="inputGroupFilePath" onChange={this.onChangeFilePath}
                                                                placeholder="File Path" />
                                                        </div>
                                                    </div>


                                                </div>


                                                <div className="card-footer">
                                                    {this.submit()}

                                                </div>


                                            </form>



                                            <table className="table table-striped" style={{ marginTop: 20 }}>
                                                <thead>
                                                    <tr>
                                                        {/* <th>ID</th> */}
                                                        <th>Line</th>
                                                        <th>Cs</th>
                                                        <th>Cnc</th>
                                                        <th>Ctc</th>
                                                        <th>Ci</th>
                                                        <th>TW</th>
                                                        <th>Cps</th>
                                                        <th>Cr</th>

                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                    </tr>


                                                    {
                                                        this.state.dataArray.map((currentData, i) => (
                                                            this.displayTable(currentData,i)
                                                            
                                                        ))
                                                    }

                                                    <tr>
                                                        <th>Total Complexity</th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th></th>
                                                        <th>{this.state.totalComplexity}</th>
                                                    </tr>


                                                </tbody>
                                            </table>
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